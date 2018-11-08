package services;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TreeJsonTest {
    @Test
    public void jsonTest() {
        String firstLv = "喷脱模剂";
        try {
            JSONObject firstOb = new JSONObject();
            JSONObject secondOb = new JSONObject();
            JSONObject lastOb = new JSONObject();
            JSONArray thirdArr = new JSONArray();
            JSONObject thirdOb = new JSONObject();
            JSONArray secondArr = new JSONArray();
            JSONArray firstArr = new JSONArray();

            //先写入根节点
            firstOb.put("name", firstLv);
            String getSec = "select distinct secondLv from tree_data where firstLv='" + firstLv + "'";
            List secList = QueryList.getList(getSec);
            for (int i = 0; i < secList.size(); i++) {
                secondOb.put("name", secList.get(i));
                String getThird = "select distinct thirdLv,type from tree_data where secondLv='" + secList.get(i) + "'";
//                List<Map> thirdList = new ArrayList();
                List<Map<String, Object>> thirdList = new ArrayList<Map<String,Object>>();
                thirdList = NewQuery.getList(getThird);
//                System.out.println(thirdList);

                for (int j = 0; j < thirdList.size(); j++) {
//                    Set<String> ks = thirdList.get(j).keySet();
                    String thirdName = thirdList.get(j).get("thirdLv").toString();
                    String thirdType = thirdList.get(j).get("type").toString();
//                    System.out.println("名称:"+thirdName+",类型:"+thirdType);
//                    Iterator<String> it = ks.iterator();
//
//                    while (it.hasNext()) {
//
//                        String key = it.next();
//                        //有了键，就可以通过map集合的get方法获取对应的值
//                        String value = thirdList.get(j).get(key).toString();
//                        System.out.println("key:" + key + "---value:" + value);
//                    }
                    thirdOb.put("name",thirdName);
                    thirdOb.put("type",thirdType);
                    String getLast = "select distinct lastLv from tree_data where thirdLv='" + thirdName + "'and secondLv='" + secList.get(i) + "'";
                    List lastList = new ArrayList();
                    lastList = QueryList.getList(getLast);
//                    System.out.println(lastList);
                    for (int k = 0; k < lastList.size(); k++) {
                        lastOb.put("name", lastList.get(k));
//                        System.out.println(lastOb);
                        //这个要put一次add一次
                        thirdArr.add(lastOb);
                    }
//                    lastOb = new JSONObject();
//                    System.out.println(thirdArr);
                    thirdOb.put("children", thirdArr);
                    //这个数组一定要清空一下,不然会重复加入
                    thirdArr = new JSONArray();
                    secondArr.add(thirdOb);
//                    thirdOb = new JSONObject();
//                    System.out.println(secondArr);
                }
                secondOb.put("children", secondArr);
                //同理这个数组也要清空
                secondArr = new JSONArray();
                firstArr.add(secondOb);
//                secondOb = new JSONObject();
//                System.out.println(firstArr);
            }
            firstOb.put("children", firstArr);
            System.out.println(firstOb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void mapTest() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("02", "zhangsan2");
        map.put("03", "zhangsan3");
        map.put("01", "zhangsan1");
        map.put("04", "zhangsan4");

        //先获取map集合的所有键的set集合，keySet();
        Set<String> k = map.keySet(); //Set<String>相当于返回值类型，此相当于Set集合加上了泛型，类型为String,k相当于变量名

        //有了Set集合，就可以获取其迭代器.（注意Set集合的类型要和迭代器保持一致）
        Iterator<String> it = k.iterator();

        while (it.hasNext()) {
            String key = it.next();
        //有了键，就可以通过map集合的get方法获取对应的值
            String value = map.get(key);
            System.out.println("key:" + key + "---value:" + value);
        }
    }
}