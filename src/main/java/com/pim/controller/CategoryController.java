package com.pim.controller;

import com.pim.constant.HttpHeader;
import com.pim.dto.CategoryCreateDTO;
import com.pim.dto.ResponseDTO;
import com.pim.model.dummy.User;
import com.pim.service.CategoryService;
import com.pim.utills.Utils;
//import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category/create", method = RequestMethod.POST)
    public ResponseDTO create(@RequestBody CategoryCreateDTO input, @RequestHeader(value = HttpHeader.REQUESTER) String requesterStr) {
        User requester = Utils.generateUserFromJsonStr(requesterStr);
        ResponseDTO result = categoryService.create(input, requester);
        return result;
    }
//useless
    @RequestMapping(value = "/category/category-list", method = RequestMethod.GET)
    public ResponseDTO getList(@RequestHeader(value = HttpHeader.REQUESTER) String requesterStr) {
        User requester = Utils.generateUserFromJsonStr(requesterStr);
        ResponseDTO result = categoryService.getList();
        return result;
    }

    //useless
    @RequestMapping(value = "/category/{id}",method = RequestMethod.GET)
    public ResponseDTO get(@PathVariable("id") long id, @RequestHeader(value = HttpHeader.REQUESTER) String requesterStr)
    {
        User requester = Utils.generateUserFromJsonStr(requesterStr);
        ResponseDTO result = categoryService.get(id);
        return result;
    }


    @RequestMapping(value = "/category/{id}",method = RequestMethod.DELETE)
    public ResponseDTO delete(@PathVariable("id") long id,@RequestHeader(value = HttpHeader.REQUESTER) String requesterStr)
    {
        User requester = Utils.generateUserFromJsonStr(requesterStr);
        ResponseDTO result = categoryService.delete(id,requester);
        return result;
    }
}