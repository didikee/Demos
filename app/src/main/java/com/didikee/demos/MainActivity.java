package com.didikee.demos;

import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.didikee.demos.ui.act.AndroidFilePathActivity;
import com.didikee.demos.ui.act.DownLoadActivity;
import com.didikee.demos.ui.act.FileChooserActivity;
import com.didikee.demos.ui.act.GooglePlayH2RVActivity;
import com.didikee.demos.ui.act.MiGuoActivity;
import com.didikee.demos.ui.act.SanJiaoActivity;
import com.didikee.demos.ui.act.WebTestActivity;
import com.didikee.demos.ui.act.YaoYiYaoActivity;

import java.util.ArrayList;
import java.util.List;

import didikee.com.permissionshelper.PermissionsHelper;
import didikee.com.permissionshelper.PermissionsHelperActivity;
import didikee.com.permissionshelper.info.DialogInfo;
import didikee.com.permissionshelper.permission.DangerousPermissions;

public class MainActivity extends PermissionsHelperActivity {
    private ArrayList<Pair<String,Class>> models=new ArrayList<>();
    @Override
    protected void setDangerousPermissions(List<String> permissions) {
        permissions.add(DangerousPermissions.STORAGE);
        permissions.add(DangerousPermissions.SENSORS);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void startFlow() {
        checkPermissions();
    }

    @Override
    protected void allPermissionsGranted() {
        init();
    }

    @Override
    protected void permissionsDenied() {
        finish();
    }

    @Override
    protected void shouldNOTShowRequest() {

    }

    @Override
    protected Boolean isFirstTime() {
        return null;
    }

    @Override
    protected DialogInfo setDialogInfo(DialogInfo dialogInfo) {
        dialogInfo.showDialog(true);
        dialogInfo.setNegativeButtonText("结束");
        dialogInfo.setPositiveButtonText("去设置");
        dialogInfo.setTitle("缺少权限");
        dialogInfo.setContent("我们需要这些必要的权限才能正常运行 =.=");
        return dialogInfo;
    }

    @Override
    protected void beforeRequestFinalPermissions(final PermissionsHelper helper) {
        Toast.makeText(this, "3秒后继续执行!", Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                helper.continueRequestPermissions();
            }
        });
    }

    private void init() {

        models.add(new Pair<String, Class>("DownLoad", DownLoadActivity.class));
        models.add(new Pair<String, Class>("文件目录", AndroidFilePathActivity.class));
        models.add(new Pair<String, Class>("摇一摇xyz轴最大值测试", YaoYiYaoActivity.class));
        models.add(new Pair<String, Class>("文件选择", FileChooserActivity.class));
        models.add(new Pair<String, Class>("shape 的三角形", SanJiaoActivity.class));
        models.add(new Pair<String, Class>("js", WebTestActivity.class));
        models.add(new Pair<String, Class>("miguo", MiGuoActivity.class));
        models.add(new Pair<String, Class>("GooglePlay 水平滚动 item一半判断", GooglePlayH2RVActivity.class));






        /***********************************Not Modify*******************************/
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class clz = models.get(position).second;
                startActivity(new Intent(MainActivity.this,clz));
            }
        });
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return models==null ? 0 :models.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, android.view.View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView==null){
                    holder=new ViewHolder();
                    convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_main,null);
                    holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                    convertView.setTag(holder);
                }
                holder= (ViewHolder) convertView.getTag();
                holder.tv_name.setText(position+1+".  "+models.get(position).first);
                return convertView;
            }
        });
    }
    private class ViewHolder{
        public TextView tv_name;
    }
}
