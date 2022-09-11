/*
 * Open Hospital (www.open-hospital.org)
 * Copyright © 2006-2021 Informatici Senza Frontiere (info@informaticisenzafrontiere.org)
 *
 * Open Hospital is a free and open source software for healthcare data management.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * https://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.isf.visits.service;

import java.util.List;

import org.isf.visits.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitsIoOperationRepository extends JpaRepository<Visit, Integer> {

	List<Visit> findAllByOrderByPatient_CodeAscDateAsc();

	List<Visit> findAllByPatient_CodeOrderByPatient_CodeAscDateAsc(@Param("patient") Integer patient);
	
	List<Visit> findAllByWardIsNullOrderByPatient_CodeAscDateAsc();

	List<Visit> findAllByWardIsNullAndPatient_CodeOrderByPatient_CodeAscDateAsc(@Param("patient") Integer patient);
	
	@Modifying
	void deleteByPatient_Code(@Param("patient") Integer patient);

	@Query(value = "SELECT * FROM VISITS WHERE VST_WRD_ID_A = :ward ORDER BY VST_DATE AND VST_ACTIVE=1", nativeQuery = true)
	List<Visit> findAllWhereWardByOrderDateAsc(@Param("ward") String ward);

	@Query(value = "SELECT * FROM VISITS WHERE VST_PAT_ID = :patient ORDER BY VST_PAT_ID, VST_DATE AND VST_ACTIVE=1", nativeQuery = true)
	List<Visit> findAllWherePatientByOrderPatientAndDateAsc(@Param("patient") Integer patient);

}