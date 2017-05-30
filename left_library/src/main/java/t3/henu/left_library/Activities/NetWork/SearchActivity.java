package t3.henu.left_library.Activities.NetWork;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import t3.henu.left_library.Activities.MusicInfo.Result;
import t3.henu.left_library.R;


public class SearchActivity extends AppCompatActivity {

    private EditText etSearchContent;
    private TextView tvSearch;
    private ListView mListViewResult;
    private ListView mListViewHistory;
    private ArrayAdapter<String> historyAdapter;
    private LinearLayout llHistory;
    private LinearLayout llResult;
    //	private String mUrl = GlobalConstants.GET_SEARCH_RESULT;
//	private ArrayList<SearchResult.ListCourse> searchResultInfo;
    private ViewStub vsNetError;
    private ViewStub vsBlankContent;


    private static final int DO_SEARCH = 1;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            SearchSong();//开始搜索操作  请求网络数据
        }
    };

    private void SearchSong() {
        String text = etSearchContent.getText().toString();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        MusicNetWork.SearchMusic(this,text,10,1,0, new MusicNetWork.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                ParaseResult(result);
            }
        });

    }

    private void ParaseResult(String result) {
        Gson gson=new Gson();
        Result re=gson.fromJson(result,Result.class);
        List<String> list=new ArrayList<String>();
        for(int i=0;i<re.getSongs().size();i++){
            list.add(re.getSongs().get(i).getName());
        }
        ArrayAdapter adapter=new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,
        android.R.id.text1,list);
        mListViewResult.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
    }


    public void initViews() {
//		vsNetError = (ViewStub) findViewById(R.id.vs_net_error);
//		vsBlankContent = (ViewStub) findViewById(R.id.vs_blank_content);


        etSearchContent = (EditText) findViewById(R.id.et_search_content);
        llHistory = (LinearLayout) findViewById(R.id.ll_search_history);
        llResult = (LinearLayout) findViewById(R.id.ll_search_result);
        mListViewHistory = (ListView) findViewById(R.id.lv_search_history);
        mListViewResult = (ListView) findViewById(R.id.lv_search_result);
        etSearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {//相关课程listview隐藏 搜索历史显示
                    llResult.setVisibility(View.GONE);
                    llHistory.setVisibility(View.VISIBLE);
                } else {//相关课程listview显示 搜索历史隐藏
                    if (llHistory.getVisibility() == View.VISIBLE) {
                        llHistory.setVisibility(View.GONE);
                    }

                }

                mHandler.sendEmptyMessageDelayed(DO_SEARCH,1000);//延迟搜索，在用户输入的时候就进行搜索，但是考虑到用户流量问题，延迟一秒
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {

                }
            }
        });

       // initSearchHistory();
    }

    private void initSearchHistory() {
       // String cache = SearchHistoryCacheUtils.getCache(SearchActivity.this);
       /* if (cache != null) {
            List<String> historyRecordList = new ArrayList<>();
            for (String record : cache.split(",")) {
                historyRecordList.add(record);
            }
            historyAdapter = new ArrayAdapter<String>(SearchActivity.this,
                    R.layout.item_search_history, historyRecordList);
            if (historyRecordList.size() > 0) {
                mListViewHistory.setAdapter(historyAdapter);
                mListViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        etSearchContent.setText("");
                        etSearchContent.setText(historyAdapter.getItem(position));
                    }
                });
            }
        } else {
            llHistory.setVisibility(View.GONE);
        }*/

    }

    public void ClearSearchHistory(View view) {
        //SearchHistoryCacheUtils.ClearCache(SearchActivity.this);
       // updateData();
    }


    public void retuen(View v){
        this.finish();
    }
}
