package com.jobs.workbook.controlllers;

import com.jobs.workbook.entites.settings.Settings;
import com.jobs.workbook.repositories.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingsController {

    @Autowired
    SettingsRepository settingsRepository;
    @CrossOrigin
    @PostMapping("/jpa/settings/register")
    public ResponseEntity<Settings> createOrUpdateSettings(@RequestBody Settings settings) {
        return new ResponseEntity<>(this.settingsRepository.save(settings), HttpStatus.ACCEPTED);
    }
}
