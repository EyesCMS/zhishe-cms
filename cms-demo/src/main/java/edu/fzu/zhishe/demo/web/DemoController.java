package edu.fzu.zhishe.demo.web;

import edu.fzu.zhishe.common.api.AjaxResponse;
import edu.fzu.zhishe.common.api.Error;
import edu.fzu.zhishe.common.api.Resource;
import edu.fzu.zhishe.common.api.UnprocessableCode;
import edu.fzu.zhishe.cms.model.CmsClubDO;
import edu.fzu.zhishe.demo.dto.CmsClubDTO;
import edu.fzu.zhishe.demo.service.DemoService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liang on 4/11/2020.
 * @version 1.0
 */
@RestController
@RequestMapping("/clubs")
public class DemoController {

    public static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

//    @ApiOperation("获取社团列表")
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ResponseEntity<List<CmsClub>> listAllClubs() {
//        return ResponseEntity.ok(demoService.listAllClubs());
//    }

    @ApiOperation("分页获取社团列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<CmsClubDO>> listClubs(
        @RequestParam(name = "page", defaultValue = "0") Integer pageNum,
        @RequestParam(name = "limit", defaultValue = "3") Integer pageSize) {
        // TODO: what if club is not exists
        List<CmsClubDO> clubs = demoService.listClubs(pageNum, pageSize);
        return ResponseEntity.ok(clubs);
    }

    @ApiOperation("创建社团")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> createClub(@Validated @RequestBody CmsClubDTO cmsClubDTO,
        BindingResult result) {

        if (result.hasErrors()) {
            Error error = new Error(Resource.CLUB.getName(), result.getFieldError().getField(),
                UnprocessableCode.INVALID.getCode());
            return ResponseEntity.unprocessableEntity().body(new AjaxResponse().errors(error));
        }

        demoService.insertClub(cmsClubDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("根据编号查找社团")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CmsClubDO> getClub(@PathVariable Integer id) {
        return ResponseEntity.ok(demoService.getClub(id));
    }

    @ApiOperation("更新社团")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateClub(@PathVariable("id") Integer id,
        @Validated @RequestBody CmsClubDTO cmsClubDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }

        // TODO: what if club is not exists
        int count = demoService.updateClub(id, cmsClubDTO);
        if (count == 1) {
            LOGGER.debug("updateClub success :{}", cmsClubDTO);
            return ResponseEntity.noContent().build();
        } else {
            LOGGER.debug("updateClub failed :{}", cmsClubDTO);
            return ResponseEntity.badRequest().body("操作失败");
        }
    }

    @ApiOperation("删除社团")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteClub(@PathVariable Integer id) {
        // TODO: what if club is not exists
        int count = demoService.deleteClub(id);
        if (count == 1) {
            LOGGER.debug("deleteClub success :id={}", id);
            return ResponseEntity.noContent().build();
        } else {
            LOGGER.debug("deleteClub failed :id={}", id);
            return ResponseEntity.badRequest().body("操作失败");
        }
    }
}
