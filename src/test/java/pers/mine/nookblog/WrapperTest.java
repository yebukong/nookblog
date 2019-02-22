package pers.mine.nookblog;/*
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import java.io.*;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.SerializationUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import pers.mine.nookblog.cast.A;
import pers.mine.nookblog.entity.Code;
import pers.mine.nookblog.utils.StringX;

public class WrapperTest implements Serializable{

    private void log(String message) {
        System.out.println(message);
    }

    private void logSqlSegment(String explain, ISqlSegment sqlSegment) {
        System.out.println(String.format(" ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓   ->(%s)<-   ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓", explain));
        System.out.println(sqlSegment.getSqlSegment());
    }

    private <T> void logParams(QueryWrapper<T> wrapper) {
        wrapper.getParamNameValuePairs().forEach((k, v) ->
                System.out.println("key: '" + k + "'\t\tvalue: '" + v + "'"));
    }

 /*   @Test
    public void  test10010(){
        B b = new B();
        b.setName("asdasd");
        System.out.println(b.test("asdasd"));
        Object a = convert1(b);
        System.out.println(a);
    }
    @Test
    public void test10086(){
        Property<Code, Object> one = Code::getRelaNo;
        Property<Code, Object> two = (Code code)->{return code.getRelaNo();};
        Object convertOne1 = SerializedLambda.convert(one);
        Object convertOne2 = convert(one);
        Object convertTwo1 = SerializedLambda.convert(two);
        Object convertTwo2 = convert(two);
        System.out.println(convertOne1.getClass() == java.lang.invoke.SerializedLambda.class);

        System.out.println(convertTwo2.getClass() == java.lang.invoke.SerializedLambda.class);
        log(convertOne1.toString());
        log(convertOne2.toString());
        log(convertTwo1.toString());
        log(convertTwo2.toString());
//        Property<Code, Object> pro = new LamTest();
//        SerializedLambda convert = SerializedLambda.convert(pro);
//        log(convert.toString());

        Code qOne = new Code();
        //按照同一code下item的顺序查询[争取相同的code项位于同一页] ps:无法完全保证
        LambdaQueryWrapper<Code> lqw =  new QueryWrapper<Code>()
                .orderByAsc(Code.TEMP_SORT_COLUMN)
                .lambda();
        Property<Code, Object> onePro = Code::getItemNo;
        Class<? extends Property> aClass = one.getClass();
        System.out.println(aClass.getName());
        //aClass.
        for (Method o : aClass.getMethods()) {
            System.out.println(o.toString());
            System.out.println(o.getDeclaredAnnotations());

            System.out.println(o.getAnnotatedReturnType());
        }
        System.out.println(one.apply(qOne));
        System.out.println(LambdaUtils.resolve(one));
        System.out.println(SerializedLambda.convert(one));
        if(!StringX.isEmpty(qOne.getItemNo())){
            //##这段代码还真是神奇啊
            lqw.or().like(onePro, qOne.getItemNo());
            lqw = lqw.or();
            Property pro = new Property<Code, String>() {
                public String apply(Code code) {
                    return code.getItemNo();
                }
            };
//            lqw = lqw.like(pro,qOne.getItemNo());
        }
        if(!StringX.isEmpty(qOne.getItemName())){
            lqw.or().like(Code::getItemName, qOne.getItemNo());
        }
        if(!StringX.isEmpty(qOne.getDescription())){
            lqw.or().like(Code::getDescription, qOne.getItemNo());
        }
    }

    class LamTest implements Property<Code, Object>, Serializable {
        public Object apply(Code code) {
            return code.getRelaNo();
        }
    }

    public static Object convert(Property lambda) {
        byte[] bytes = SerializationUtils.serialize(lambda);
        try (ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(bytes)) {
            @Override
            protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                Class<?> clazz = super.resolveClass(objectStreamClass);
                System.out.print(clazz.getName() +"-->");
                if(clazz == java.lang.invoke.SerializedLambda.class){
                    clazz =  SerializedLambda.class;
                }
                System.out.println(clazz.getName());
                return  clazz;
            }
        }) {
            return objIn.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw ExceptionUtils.mpe("This is impossible to happen", e);
        }
    }*/

    public static Object convert1(B obj) {
        byte[] bytes = SerializationUtils.serialize(obj);
        try (ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(bytes)) {
            @Override
            protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                Class<?> clazz = super.resolveClass(objectStreamClass);
                System.out.print(clazz.getName() +"-->");
                if(clazz==B.class){
                    clazz = A.class;
                }
                System.out.println(clazz.getName());
                return clazz;
            }
        }) {
            return objIn.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw ExceptionUtils.mpe("This is impossible to happen", e);
        }
    }

    @Test
    public void test() {
        Wrapper<Code> wrapper = new QueryWrapper<Code>().lambda().eq(Code::getItemName, 123)
                .or(c -> c.eq(Code::getItemNo, 1).eq(Code::getId, 2))
                .eq(Code::getId, 1);
        log(wrapper.getSqlSegment());

    }

    @Test
    public void test1() {
        QueryWrapper<Code> ew = new QueryWrapper<Code>()
                .eq("xxx", 123)
                .and(i -> i.eq("andx", 65444).le("ande", 66666))
                .ne("xxx", 222);
        log(ew.getSqlSegment());
        ew.getParamNameValuePairs().forEach((k, v) -> System.out.println("key = " + k + " ; value = " + v));
    }

    @Test
    public void test2() {
        Code code = new Code();
        code.setItemNo("test");
        UpdateWrapper<Code> uw = new UpdateWrapper<Code>(code);
        //set 为update需要更新的字段  其他未where语句限定条件
        uw.set("id", code.getId())
                .set("description", code.getDescription())
                .eq("itemno", 123)
                .and(i -> i.eq("itemName", 65444).le("itemName", 66666))
                .ne("itemno", 222);
        //boolean result = service.update(new Code(),uw);

        UpdateWrapper<Code> ew = new UpdateWrapper<Code>()
                .set("name", "三毛").set("id", 1)
                .eq("xxx", 123)
                .and(i -> i.eq("andx", 65444).le("ande", 66666))
                .ne("xxx", 222);

        log(ew.getSqlSet());
        log(ew.getSqlSegment());
        log(ew.getParamAlias());
    }

    @Test
    public void test3() {
        UpdateWrapper<Code> ew = new UpdateWrapper<Code>()
                .setSql("abc=1,def=2").eq("id", 1).ge("age", 3);
        log(ew.getSqlSet());
        log(ew.getSqlSegment());
    }

    @Test
    public void testQueryWrapper() {
        logSqlSegment("去除第一个 or,以及自动拼接 and,以及手动拼接 or,以及去除最后的多个or", new QueryWrapper<Code>().or()
                .ge("age", 3).or().ge("age", 3).ge("age", 3).or().or().or().or());

        logSqlSegment("多个 or 相连接,去除多余的 or", new QueryWrapper<Code>()
                .ge("age", 3).or().or().or().ge("age", 3).or().or().ge("age", 3));

        logSqlSegment("嵌套,正常嵌套", new QueryWrapper<Code>()
                .nested(i -> i.eq("id", 1)).eq("id", 1));

        logSqlSegment("嵌套,第一个套外的 and 自动消除", new QueryWrapper<Code>()
                .and(i -> i.eq("id", 1)).eq("id", 1));

        logSqlSegment("嵌套,多层嵌套", new QueryWrapper<Code>()
                .and(i -> i.eq("id", 1).and(j -> j.eq("id", 1))));

        logSqlSegment("嵌套,第一个套外的 or 自动消除", new QueryWrapper<Code>()
                .or(i -> i.eq("id", 1)).eq("id", 1));

        logSqlSegment("嵌套,套内外自动拼接 and", new QueryWrapper<Code>()
                .eq("id", 11).and(i -> i.eq("id", 1)).eq("id", 1));

        logSqlSegment("嵌套,套内外手动拼接 or,去除套内第一个 or", new QueryWrapper<Code>()
                .eq("id", 11).or(i -> i.or().eq("id", 1)).or().eq("id", 1));

        logSqlSegment("多个 order by 和 group by 拼接,自动优化顺序,last方法拼接在最后", new QueryWrapper<Code>()
                .eq("id", 11)
                .last("limit 1")
                .orderByAsc("id", "name", "sex").orderByDesc("age", "txl")
                .groupBy("id", "name", "sex").groupBy("id", "name"));

        logSqlSegment("只存在 order by", new QueryWrapper<Code>()
                .orderByAsc("id", "name", "sex").orderByDesc("age", "txl"));

        logSqlSegment("只存在 group by", new QueryWrapper<Code>()
                .groupBy("id", "name", "sex").groupBy("id", "name"));
    }

    @Test
    public void testCompare() {
        QueryWrapper<Code> queryWrapper = new QueryWrapper<Code>()
                .allEq(getMap()).allEq((k, v) -> true, getMap())
                .eq("id", 1).ne("id", 1)
                .or().gt("id", 1).ge("id", 1)
                .lt("id", 1).le("id", 1)
                .or().between("id", 1, 2).notBetween("id", 1, 3)
                .like("id", 1).notLike("id", 1)
                .or().likeLeft("id", 1).likeRight("id", 1);
        logSqlSegment("测试 Compare 下的方法", queryWrapper);
        logParams(queryWrapper);
    }

    @Test
    public void testFunc() {
        QueryWrapper<Code> queryWrapper = new QueryWrapper<Code>()//todo in 方法是不是各个加个后缀好点
                .isNull("nullColumn").or().isNotNull("notNullColumn")
                .orderByAsc("id").orderByDesc("name")
                .groupBy("id", "name").groupBy("id2", "name2")
                .in("inColl", getList()).or().notIn("notInColl", getList())
                .in("inArray").notIn("notInArray", 1, 2, 3)
                .inSql("inSql", "1,2,3,4,5").notInSql("inSql", "1,2,3,4,5")
                .having("sum(age) > {0}", 1).having("id is not null");
        logSqlSegment("测试 Func 下的方法", queryWrapper);
        logParams(queryWrapper);
    }

    @Test
    public void testJoin() {
        QueryWrapper<Code> queryWrapper = new QueryWrapper<Code>()
                .last("limit 1").or()
                .apply("date_format(column,'%Y-%m-%d') = '2008-08-08'")
                .apply("date_format(column,'%Y-%m-%d') = {0}", LocalDate.now())
                .or().exists("select id from table where age = 1")
                .or().notExists("select id from table where age = 1");
        logSqlSegment("测试 Join 下的方法", queryWrapper);
        logParams(queryWrapper);
    }

    @Test
    public void testNested() {
        QueryWrapper<Code> queryWrapper = new QueryWrapper<Code>()
                .and(i -> i.eq("id", 1).nested(j -> j.ne("id", 2)))
                .or(i -> i.eq("id", 1).and(j -> j.ne("id", 2)))
                .nested(i -> i.eq("id", 1).or(j -> j.ne("id", 2)));
        logSqlSegment("测试 Nested 下的方法", queryWrapper);
        logParams(queryWrapper);
    }

    @Test
    public void testPluralLambda() {
        TableInfoHelper.initTableInfo(null, Code.class);
        QueryWrapper<Code> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Code::getItemName,"sss");
        queryWrapper.lambda().eq(Code::getItemName,"sss2");
        logSqlSegment("测试 PluralLambda", queryWrapper);
        logParams(queryWrapper);
    }

    private List<Object> getList() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(i);
        }
        return list;
    }

    private Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            map.put("column" + i, i);
        }
        map.put("nullColumn", null);
        return map;
    }

//    public void test() {
//        String sql = new QueryWrapper()
//            .where("b.age > 18", condition ->
//                condition.and("b.type = 'rabid'")
//                    .or(nested -> nested.apply("name='12'").and("age=1"))
//                    .notIn("ads,2112,212")
//                    .last("LIMIT 1")
//            ).sqlSegment();
//
//        log(sql);
//        assertEquals("WHERE b.age > 18 AND b.type = 'rabid' OR ( name='12' AND age=1 ) NOT IN ( ads,2112,212 ) LIMIT 1", sql);
//    }

}
