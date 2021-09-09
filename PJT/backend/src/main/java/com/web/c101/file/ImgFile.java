package com.web.c101.file;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Getter
@Table(name="file")
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImgFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fid;

    private String file_name;
    private String file_size;

    private long rid;

}