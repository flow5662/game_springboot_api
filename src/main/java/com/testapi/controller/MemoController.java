package com.testapi.controller;

import com.testapi.entity.Memo;
import com.testapi.service.MemoService;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

//    @CrossOrigin(origins = "*", methods = {
//            RequestMethod.GET,
//            RequestMethod.POST
//            , RequestMethod.OPTIONS}, allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"},
//            exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})
// CORS 이슈가 아닌 프론트 이슈로 해결 완료.

    @CrossOrigin(origins = "*",allowedHeaders = "*") //CORS 이슈 어노테이션 필요.
    @RestController
    @RequestMapping("/api")
public class MemoController {

    @Autowired
    private MemoService memoService;

    @PostMapping("/insert")
    public Memo insertMemo(@RequestParam("file") MultipartFile file
    ,@RequestParam("pw") String pw,@RequestParam("title")String title,@RequestParam("text") String text)
    throws IOException {
        System.out.println("controller:"+title);
        return memoService.saveMemo(file,title,text,pw);
    }



    @GetMapping
    public List<Memo> getMemo(){return memoService.getMemo();}

    @GetMapping("/{id}")
    public Optional<Memo> getMemo(@PathVariable("id") Long id){return memoService.getIdMemo(id);}

    record NewMemo(Long id,byte[] image,String pw,String title,String text){}

    @PutMapping("/update/{id}")
    public Memo updateMemo(@PathVariable("id") Long id,@RequestParam("file") MultipartFile file
            ,@RequestParam("title")String title,@RequestParam("text") String text)throws IOException{
        return memoService.updateMemo(id,file,title,text);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMemo(@PathVariable Long id){
        memoService.deleteMemo(id);
    }

}
