package com.learning.model;

import com.learning.constant.Priority;
import com.learning.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tickets")
public class Ticket implements Serializable {

    @Id
    private Long id;
    private String summary;
    private Priority priority;
    private String category;
    private String description;
    private String userId;
    private Status status;

}
