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
  long id;

  @Lob
  byte[] content;

  @Column(length = 500, nullable = false, unique = true)
  String name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Image() {
  }

  // public Image(byte[] content, String name) {
  //   this.content = content;
  //   this.name = name;
  // }

  public String generateBase64Image()
	{
		return Base64.encodeBase64String(this.getContent());
	}
}
