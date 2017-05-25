package t3.henu.left_library.Activities.NetWork;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
        finish();
    }
}
