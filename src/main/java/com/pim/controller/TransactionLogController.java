package com.pim.controller;

import com.pim.constant.HttpHeader;
import com.pim.dto.*;
import com.pim.model.dummy.User;
import com.pim.service.TransactionLogService;
import com.pim.utills.Utils;
//import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionLogController {
    @Autowired
    private TransactionLogService transactionLogService;

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ResponseDTO create(@RequestBody TransactionLogCreateDTO input, @RequestHeader(value = HttpHeader.REQUESTER) String requesterStr) {
        User requester = Utils.generateUserFromJsonStr(requesterStr);
        ResponseDTO result = transactionLogService.create(input, requester);
        return result;
    }

    @RequestMapping(value = "/transaction/transaction-list", method = RequestMethod.GET)
    public ResponseDTO getList(@RequestHeader(value = HttpHeader.REQUESTER) String requesterStr) {
        User requester = Utils.generateUserFromJsonStr(requesterStr);
        ResponseDTO result = transactionLogService.getList();
        return result;
    }

    @RequestMapping(value = "/transaction/{id}",method = RequestMethod.GET)
    public ResponseDTO get(@PathVariable("id") long id, @RequestHeader(value = HttpHeader.REQUESTER) String requesterStr)
    {
        User requester = Utils.generateUserFromJsonStr(requesterStr);
        ResponseDTO result = transactionLogService.get(id);
        return result;
    }

    //useless api
    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.PUT)
    public ResponseDTO update(@PathVariable("id") long prodId , @RequestBody TransactionLogUpdateDTO input, @RequestHeader(value = HttpHeader.REQUESTER) String requesterStr)
    {
        User requester = Utils.generateUserFromJsonStr(requesterStr);
        ResponseDTO result = transactionLogService.update(input, prodId, requester);

        return result;
    }

    @RequestMapping(value = "/checkout/{id}",method = RequestMethod.DELETE)
    public ResponseDTO delete(@PathVariable("id") long id,@RequestHeader(value = HttpHeader.REQUESTER) String requesterStr)
    {
        User requester = Utils.generateUserFromJsonStr(requesterStr);
        ResponseDTO result = transactionLogService.delete(id,requester);
        return result;
    }



}
