package com.pim.repository;

import com.pim.model.TransactionLog;
//import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//@Repository
//public interface TransactionRepository extends MongoRepository <TransactionLog, String>
public interface TransactionRepository extends JpaRepository<TransactionLog, String>
{
    public TransactionLog findByCustomerNameAndStatus(String name, String status);
    List<TransactionLog> findAllByStatus(String status);
    public TransactionLog findByIdAndStatus(long id, String status);

}
