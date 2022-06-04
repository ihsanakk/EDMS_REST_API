package com.tempter.edms_rest_api.dao;

import com.tempter.edms_rest_api.entity.Signature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureDAO extends JpaRepository<Signature, Integer> {
}
