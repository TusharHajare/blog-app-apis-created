package com.example.blogappapis.entites;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Posts")
public class Post
{
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int postId;

 @Column(name = "post_title", length = 100, nullable = false)
 private String title;

 @Column(length = 1000)
 private String content;

 @Column(name = "Image", length = 100)
 private String imageName;

 @Column(name = "Date")
 private Date addedDate;

 @ManyToOne
 @JoinColumn(name = "category_id")//if you not want auto name of coloumn then use join colomn
 private Category category;
 @ManyToOne
 private User user;

 @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
 private Set<Comment> comments = new HashSet<>();

}
