package yu.softwareDesign.TripTip.domain.baseModel;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseDateEntity {
    @CreatedDate @Column(name="create_date", updatable = false)
    private LocalDateTime created_date;

    @LastModifiedDate @Column(name="last_modified_date", updatable = true)
    private LocalDateTime last_modified_date;
}
