
package com.twopillar.jiba.fragment;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.twopillar.jiba.R;
import com.twopillar.jiba.activity.ActionDetailActivity;
import com.twopillar.jiba.common.ViewHolder;
import com.twopillar.jiba.model.Action;

/**
 * @author GuoJian'an
 * @date 2015-7-14
 * 动作分类
 */
@SuppressLint("ValidFragment")
public class ActionCategoreyFragment extends Fragment
{
    private int index;//指示器坐标
    private ListView lv_actionList;//动作列表;
    
    private ActionAdatper actionAdatper;
    
    private List<Action> actions = new ArrayList<Action>();
    
    private String actionType;//动作类型
    
    private String title;//标题名
    
    private View view;
    

    @SuppressLint("ValidFragment")
    public ActionCategoreyFragment(int index)
    {
        this.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        
        view = inflater.inflate(R.layout.activity_action_list, container, false);
        actions.clear();
        switch (index)
        {
            case 0:
                actionType = "shoulder";
                break;
            case 1:
                actionType = "thorax";
                break;
            case 2:
                actionType = "bicipital";
                break;
            case 3:
                actionType = "triceps";
                break;
            case 4:
                actionType = "back";
                break;
            case 5:
                actionType = "abdominal";
                break;
            case 6:
                actionType = "leg";
                break;
            case 7:
                actionType = "aerobic";
                break;
            default:
                break;
        }
        initView();
        initData();
        setListener();
        return view;
    }
    
    
    private void initView() {
        lv_actionList = (ListView)view.findViewById(R.id.lv_actionList);
    }
    
    @SuppressLint("NewApi")
    private void initData() {
        actions = DataSupport.where("bigtype = ?", actionType).find(Action.class);
        actionAdatper = new ActionAdatper(getActivity(), R.layout.item_action, actions);
        lv_actionList.setAdapter(actionAdatper);
        actionAdatper.notifyDataSetChanged();
    }
    
    private void setListener() {
        lv_actionList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Action action = actions.get(position);
                Intent intent = new Intent(getActivity(),ActionDetailActivity.class);
                intent.putExtra("video", action.getDrawableId());
                intent.putExtra("title", action.getActionName());
                intent.putExtra("description", action.getDescription());
                startActivity(intent);
            }
        });
    }
    
    @SuppressLint("NewApi")
    private class ActionAdatper extends ArrayAdapter<Action> {
        
        private int resourceId;
        
        private MediaMetadataRetriever retriever;
        
        private Uri uri;
        
        public ActionAdatper(Context context, int resource, List<Action> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Action action = getItem(position);
            if(convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            }
                ImageView iv_action = ViewHolder.get(convertView, R.id.iv_action);
                iv_action.setImageResource(action.getDrawablePicId());
                TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
                tv_name.setText(action.getActionName());
          
                return convertView;
        }
    }

}
