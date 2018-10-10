package services;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TreeJsonTest {
    @Test
    public void jsonTest(){
        String firstLv = "喷脱模剂";
        JSONObject firstOb = new JSONObject();
        JSONObject secondOb = new JSONObject();
        JSONObject lastOb = new JSONObject();
        JSONArray thirdArr = new JSONArray();
        JSONObject thirdOb = new JSONObject();
        JSONArray secondArr = new JSONArray();
        JSONArray firstArr = new JSONArray();
        //先写入根节点
        firstOb.put("name", firstLv);
        try {
            String getSec = "select distinct secondLv from tree_data where firstLv='"+firstLv+"'";
            List secList = QueryList.getList(getSec);
//            for(int i= 0; i<secList.size();i++){
//                secondOb.put("name",secList.get(i));
//                String getThird = "select distinct thirdLv from tree_data where secondLv='"+secList.get(i)+"'";
//                List thirdList = new ArrayList();
//                thirdList = QueryList.getList(getThird);
//                for(int j=0; j<thirdList.size();j++){
//                    thirdOb.put("name",thirdList.get(j));
//                    String getLast = "select distinct lastLv from tree_data where thirdLv='"+thirdList.get(j)+"'and secondLv='"+secList.get(i)+"'";
//                    List lastList = new ArrayList();
//                    lastList = QueryList.getList(getLast);
//                    System.out.println(lastList);
//                    for(int k=0;k<lastList.size();k++){
//                        lastOb.put("name",lastList.get(k));
////                        System.out.println(lastOb);
//                        //这个要put一次add一次
//                        thirdArr.add(lastOb);
//                    }
////                    lastOb = new JSONObject();
////                    System.out.println(thirdArr);
//                    thirdOb.put("children",thirdArr);
//                    //这个数组一定要清空一下,不然会重复加入
//                    thirdArr = new JSONArray();
//                    secondArr.add(thirdOb);
////                    thirdOb = new JSONObject();
////                    System.out.println(secondArr);
//                }
//                secondOb.put("children",secondArr);
//                //同理这个数组也要清空
//                secondArr = new JSONArray();
//                firstArr.add(secondOb);
////                secondOb = new JSONObject();
////                System.out.println(firstArr);
//            }
            firstOb.put("children",firstArr);
            System.out.println(firstOb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}