package com.testapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.util.Base64Util;
import org.apache.tomcat.util.codec.binary.Base64;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Memo {
    @Id
    @SequenceGenerator(
            name = "id_sequence",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_sequence"
    )
    private Long id;
    private String title;
    private String text;
    private String pw;
    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private  byte[] image;

    public String getImageBase64() {
        return Base64.encodeBase64String(this.image);
    }

}
