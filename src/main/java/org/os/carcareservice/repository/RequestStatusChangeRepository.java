package org.os.carcareservice.repository;

import org.os.carcareservice.entity.RequestStatusChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestStatusChangeRepository extends JpaRepository<RequestStatusChange, Integer> {

    List<RequestStatusChange> findByRequest_RequestId(Integer requestId);

    List<RequestStatusChange> findByRequest_RequestIdOrderByCreatedAtAsc(Integer requestId);
}
