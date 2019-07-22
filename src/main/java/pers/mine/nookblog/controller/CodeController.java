package pers.mine.nookblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.mine.nookblog.entity.Code;
import pers.mine.nookblog.service.ICodeService;
import pers.mine.nookblog.utils.StringX;

import java.util.List;

//在类上使用注解@RestController
//在方法上使用注解@ResponseBody
//这个只是必要条件，却不是设置响应消息类型的方法。
@RestController
@RequestMapping("/code")
@Slf4j
public class CodeController extends ApiController {


    @Autowired
    private ICodeService service;

    @GetMapping("/list")
    public R<List<Code>> list() {
        LambdaQueryWrapper<Code> lqw = new LambdaQueryWrapper<>();
        //noinspection unchecked
        lqw.orderByAsc(Code::getCreated);
        List<Code> list = service.list(lqw);
        return R.ok(list);
    }

    /**
     * <p>
     * 参数模式分页
     * </p>
     * <p>
     * 7、分页 size 一页显示数量  current 当前页码
     * 方式一：url?size=1&current=1<br>
     * <p>
     * 集合模式，不进行分页直接返回所有结果集：
     * url?listMode=true
     */
    @GetMapping("/page")
    public R<IPage<Code>> page(Page<Code> page, boolean listMode) {
        if (listMode) {
            // size 小于 0 不在查询 total 及分页，自动调整为列表模式。
            // 注意！！这个地方自己控制好！！
            page.setSize(-1);
        }
        return R.ok(service.page(page, null));
    }


    @GetMapping(value = "/treeData", produces = "application/json;charset=UTF-8")
    public R<IPage<Code>> treeData(Page<Code> page, Code qOne, boolean hasKey) {
        //按照同一code下item的顺序查询[争取相同的code项位于同一页] ps:无法完全保证
        QueryWrapper<Code> lqw = new QueryWrapper<Code>()
                .orderByAsc(Code.TEMP_SORT_COLUMN);
        //##这段代码还真是神奇啊
        if(hasKey){
            //mp有个改进的小细节,如果and内sql为空，and的空号可以不用添加
            lqw.lambda().and(e -> {
                if (!StringX.isEmpty(qOne.getItemNo())) e.or().like(Code::getItemNo, qOne.getItemNo());
                if (!StringX.isEmpty(qOne.getItemName()))  e.or().like(Code::getItemName, qOne.getItemName());
                if (!StringX.isEmpty(qOne.getRelaNo()))  e.or().like(Code::getRelaNo, qOne.getRelaNo());
                if (!StringX.isEmpty(qOne.getValue()))  e.or().like(Code::getValue, qOne.getValue());
                if (!StringX.isEmpty(qOne.getDescription())) e.or().like(Code::getDescription, qOne.getDescription());
                return e;
            });
        }
        IPage<Code> codes = service.page(page, lqw);
        return R.ok(codes);
    }

    @PostMapping("/add")
    public R<Code> save(Code one) {
        String relano = one.getRelaNo();
        String itemno = StringX.nvl(one.getItemNo(), "");
        if (itemno.equals(relano)) {
            return R.failed("代码项编号与关联编号不能相同");
        }
        Code checkCode = service.getCodeItem(one.getRelaNo(), one.getItemNo());
        if (checkCode != null) {//唯一性校验：相同relano下itemno需要唯一
            return R.failed("代码项[" + (StringX.isEmpty(relano) ? "" : relano + ".") + one.getItemNo() + "]已存在");
        }
        boolean result = service.save(one);
        return result ? R.ok(one) : R.failed("新增出错");
    }

    @PostMapping("/update")
    public R<Code> updateById(Code one) {
        String relano = one.getRelaNo();
        String itemno = StringX.nvl(one.getItemNo(), "");
        if (itemno.equals(relano)) {
            return R.failed("代码项编号与关联编号不能相同");
        }
        LambdaQueryWrapper<Code> lqw = new LambdaQueryWrapper<>();
        lqw.ne(Code::getId, one.getId());
        lqw.eq(Code::getItemNo, one.getItemNo());
        if (StringX.isEmpty(relano)) {
            lqw.isNull(Code::getRelaNo);
        } else {
            lqw.eq(Code::getRelaNo, relano);
        }
        List<Code> codeItems = service.list(lqw);//唯一性校验：相同relano下itemno需要唯一
        if (codeItems != null && codeItems.size() > 0) {
            return R.failed("代码项[" + (StringX.isEmpty(relano) ? "" : relano + ".") + one.getItemNo() + "]已存在");
        }
        boolean result = service.updateById(one);
        return result ? R.ok(one) : R.failed("更新失败：主键有误？");
    }

    @PostMapping("/relaDel") //级联删除
    public R<Code> relaDel(Code one) {
        String relano = one.getRelaNo();
        if (StringX.isEmpty(relano)) {//如果是code项
            LambdaQueryWrapper<Code> lqw = new LambdaQueryWrapper<Code>()
                    .eq(Code::getId, one.getId())
                    .eq(Code::getRelaNo, one.getItemNo());
            service.remove(lqw);
        }
        boolean result = service.removeById(one);
        return result ? R.ok(one) : R.failed("删除出错");
    }

    @GetMapping("/getCodeItems")
    public R<List<Code>> getItems(String codeNo) {
        return R.ok(service.getCodeItems(codeNo));
    }

    @PostMapping("/updateStatus")
    public R<Code> updateStatusById(Code one) {
        boolean result = service.updateStatusById(one);
        return result ? R.ok(one) : R.failed("更新状态失败：主键有误？");
    }
}
