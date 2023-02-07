package com.vungdo.tacocloudmaven.data;

import com.vungdo.tacocloudmaven.domain.model.BunPhoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<BunPhoOrder, String> {

}