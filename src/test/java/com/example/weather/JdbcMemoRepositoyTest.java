package com.example.weather;

import com.example.weather.domain.Memo;
import com.example.weather.repository.JdbcMemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class JdbcMemoRepositoyTest {
    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest(){
        //when
        Memo newMemo = new Memo(2,"this is new memo-");
        //given
        jdbcMemoRepository.save(newMemo);
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        //then
        assertEquals(result.get().getText(),"this is new memo-");
    }
    @Test
    void findByIdTest(){
        //when
        List<Memo> memoList = jdbcMemoRepository.findAll();
        //given

        //then
        assertNotNull(memoList);


    }


}
