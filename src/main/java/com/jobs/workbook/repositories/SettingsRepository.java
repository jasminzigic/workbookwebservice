package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.settings.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings, Integer> {
    Settings findById(long id);
}
