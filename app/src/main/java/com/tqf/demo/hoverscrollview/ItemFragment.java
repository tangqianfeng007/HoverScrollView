package com.tqf.demo.hoverscrollview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author tangqianfeng
 *
 */
public class ItemFragment extends Fragment implements HoverScrollView.OverScrollController {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String[] arr = { "孙悟空", "猪八戒", "唐僧" ,"孙悟空", "猪八戒", "唐僧","孙悟空", "猪八戒", "唐僧","孙悟空", "猪八戒"
            , "唐僧","孙悟空", "猪八戒", "唐僧","孙悟空", "猪八戒", "唐僧","孙悟空", "猪八戒", "唐僧","孙悟空", "猪八戒",
            "唐僧","孙悟空", "猪八戒", "唐僧","孙悟空", "猪八戒", "唐僧"};

    private ListView listView;
    private boolean isTop;


    public ItemFragment() {
    }

    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        Context context = view.getContext();
        listView = (ListView) view;
        listView.setAdapter(new ArrayAdapter<String>(context , android.R.layout.simple_list_item_1 , arr));
        listView.setFocusable(false);
        listView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int childTop = 0;
                if (view.getChildCount() > 0) {
                    childTop = view.getChildAt(0).getTop();
                }
                isTop = firstVisibleItem == 0 && childTop == 0;
            }
        });
        return view;
    }

    @Override
    public boolean canScrollUp() {
        return isTop;
    }

}
