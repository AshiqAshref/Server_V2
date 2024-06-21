package com.MainServer.Server_V2.modeB.repository;

import com.MainServer.Server_V2.modeB.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {

    Optional<Medicine> findMedicineByMedName(String medbName);
    Optional<Medicine> findMedicineByMedBoxNo(int medbBoxNo);

//    @Transactional
//    @Modifying
//    @Query("update Medicine m set m.medbBoxNo = ?2 where m.medb_id = ?1")
//    int setMedbAmountFor(Long medbId, short medbBoxNo);
}
