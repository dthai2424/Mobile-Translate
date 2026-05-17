package com.example.translator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ProgressBar;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SavedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayout layoutEmpty;
    private SavedAdapter adapter;
    private final List<SavedTranslation> translationList = new ArrayList<>();
    private ImageButton btnBack;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        recyclerView = findViewById(R.id.recyclerViewSaved);
        progressBar = findViewById(R.id.progressBar);
        layoutEmpty = findViewById(R.id.layoutEmpty);
        btnBack     = findViewById(R.id.btnBackSaved);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SavedAdapter(translationList);
        recyclerView.setAdapter(adapter);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(SavedActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
        fetchSavedTranslations();
    }

    private void fetchSavedTranslations() {
        String token = AuthManager.getInstance(this).getToken();
        if (token == null) {
            Toast.makeText(this, "Yêu cầu đăng nhập", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        executor.execute(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL("http://10.0.2.2:8080/api/translations/my-translations");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setRequestProperty("Accept", "application/json");

                int code = conn.getResponseCode();
                if (code == HttpURLConnection.HTTP_OK) {
                    try (Scanner scanner = new Scanner(conn.getInputStream(), "UTF-8")) {
                        String raw = scanner.useDelimiter("\\A").next();
                        JSONArray jsonArray = new JSONArray(raw);

                        List<SavedTranslation> fetchedList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            SavedTranslation st = new SavedTranslation();
                            st.sourceLang = obj.getString("sourceLang");
                            st.sourceText = obj.getString("sourceText");
                            st.targetLang = obj.getString("targetLang");
                            st.targetText = obj.getString("targetText");
                            fetchedList.add(st);
                        }

                        mainHandler.post(() -> {
                            translationList.clear();
                            translationList.addAll(fetchedList);
                            adapter.notifyDataSetChanged();

                            progressBar.setVisibility(View.GONE);
                            if (translationList.isEmpty()) {
                                layoutEmpty.setVisibility(View.VISIBLE);
                            } else {
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                } else {
                    mainHandler.post(() -> {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SavedActivity.this, "Lỗi lấy dữ liệu: " + code, Toast.LENGTH_SHORT).show();
                        layoutEmpty.setVisibility(View.VISIBLE);
                    });
                }
            } catch (Exception e) {
                mainHandler.post(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SavedActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    layoutEmpty.setVisibility(View.VISIBLE);
                });
            } finally {
                if (conn != null) conn.disconnect();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}