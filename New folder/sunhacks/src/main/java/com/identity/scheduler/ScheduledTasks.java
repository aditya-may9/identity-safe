package com.identity.scheduler;

import com.identity.pojo.MaskedSecrets;
import com.identity.repository.MaskedSecretsRepository;
import com.identity.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ScheduledTasks {

    @Autowired
    MaskedSecretsRepository maskedSecretsRepository;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void removeExpiredMaskedSecrets()  {
        Long currentTime = GlobalUtils.getCurrentTimestamp();
        System.out.println(currentTime);
        List<MaskedSecrets> maskedSecrets = maskedSecretsRepository.findByActiveUntilLessThan(currentTime);
        for (MaskedSecrets maskedSecret: maskedSecrets) {
            maskedSecretsRepository.delete(maskedSecret);
        }
    }
}
