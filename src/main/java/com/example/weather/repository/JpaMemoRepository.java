package com.example.weather.repository;

import com.example.weather.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemoRepository extends JpaRepository<Memo, Integer> {
}
