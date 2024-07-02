package com.project_t.task.models;

import org.apache.tomcat.util.codec.binary.Base64;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 65, nullable = true)
  private String image;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Image() {
  }

  public Image(String image) {
    this.image = image;
  }

  public Image(long id, String image) {
    this.id = id;
    this.image = image;
  }

  // public String generateBase64Image() {
  // return Base64.encodeBase64String(this.getContent());
  // }
}
