package com.example.bloodpressureapp.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

	public interface BloodpressureRepository extends CrudRepository<Bloodpressure, Long> {

		List<Bloodpressure> findById(long bloodpressureid);
		List<Bloodpressure> findByUser(User user);

}
