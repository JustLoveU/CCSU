package com.lomtom.search.common.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: LOMTOM
 * @Date: 2021/5/13
 * @Time: 22:51
 */

@CanalEventListener
public class InfoListener {


    @Autowired
    private MQListener mqListener;



    @ListenPoint(schema = "easy_abroad", table = {"info"})
    public void infoChange(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        long preId = 0;
        long lastId = 0;
        //修改前数据
        for(CanalEntry.Column column: rowData.getBeforeColumnsList()) {
            if ("id".equals(column.getName())){
                preId = Long.parseLong(column.getValue());
                break;
            }
        }

        //修改后数据
        for(CanalEntry.Column column: rowData.getAfterColumnsList()) {
            if ("id".equals(column.getName())){
                lastId = Long.parseLong(column.getValue());
                break;
            }
        }


        if (eventType.getNumber() == 1) {
            mqListener.addInfo(lastId);
        }
        if (eventType.getNumber() == 2) {
            mqListener.updateInfo(lastId);
        }
        if (eventType.getNumber() == 3) {
            mqListener.deleteInfo(preId);
        }
    }

}