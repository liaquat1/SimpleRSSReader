package ca.nismit.simplerssreader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ca.nismit.simplerssreader.R;
import ca.nismit.simplerssreader.adapter.EditAdapter;
import ca.nismit.simplerssreader.orma.FeedUrlStore;

public class FeedsListFragment extends Fragment {
    private static final String TAG = FeedsListFragment.class.getSimpleName();

    public ListView mListView;
    private EditAdapter editAdapter;
    private List<FeedUrlStore> list;

    public FeedsListFragment() { }

    public static FeedsListFragment newInstance() { return new FeedsListFragment(); }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initOrma();
        list = FeedUrlStore.relationGetAll(FeedUrlStore.getRelation());
        initListView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feeds_list, container, false);
        mListView = (ListView) v.findViewById(R.id.e_listview);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        mListView.setAdapter(editAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(),"Touched!"+list.get(position).id,Toast.LENGTH_SHORT).show();
                // TODO
                // replace new fragment to edit table
            }
        });
    }

    void initListView() {
        editAdapter = new EditAdapter(
                getContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                list);

    }

    void initOrma() {
        FeedUrlStore.initRelaion(getContext());
    }
}