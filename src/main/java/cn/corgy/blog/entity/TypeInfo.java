package cn.corgy.blog.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TypeInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String introduction;
}
