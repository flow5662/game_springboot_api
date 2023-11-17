package com.testapi.service;

import com.testapi.entity.Memo;
import com.testapi.repository.MemoRepository;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class MemoService {
    @Autowired
    private MemoRepository memoRepository;
    
    public Memo saveMemo(MultipartFile file,String title,String text,String pw)throws IOException{
        System.out.println("service:"+title);
        Memo memo = new Memo();
        memo.setPw(pw);
        memo.setTitle(title);
        memo.setText(text);
        memo.setImage(file.getBytes());
        System.out.println("memo:"+memo.getTitle()); //값 중복 이슈

        return memoRepository.save(memo);
    }

    public List<Memo> getMemo(){
       return memoRepository.findAll();
    }

    public Optional<Memo> getIdMemo(Long id){
        return memoRepository.findById(id);
    }


    public Memo updateMemo(Long id,MultipartFile file, String title,String text ) throws IOException {
        Memo newMemo = new Memo();
        newMemo.setImage(file.getBytes());
        newMemo.setTitle(title);
        newMemo.setText(text);
        System.out.println("update:"+title);
        return memoRepository.findById(id).map(memo -> {
               if(file != null && !file.isEmpty()){
                   memo.setImage(newMemo.getImage());
               }
                memo.setTitle(newMemo.getTitle());
                memo.setText(newMemo.getText());
                return memoRepository.save(memo);
        }).orElseGet(() -> {
            newMemo.setId(id);
            return memoRepository.save(newMemo);
        });
    }

    public void deleteMemo(Long id){memoRepository.deleteById(id);}
}
