package io.prbaa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String familyName;
    private String jobTitle;
    private String email;
    private String phone;
    private String imageUrl;
    @Column(nullable = false,updatable = false)
    private String familyCode;
}
