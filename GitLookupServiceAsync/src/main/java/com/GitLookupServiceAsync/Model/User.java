package com.GitLookupServiceAsync.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


// The @JsonIgnoreProperties annotation tells Spring to ignore any attributes not listed in the class.
// In this guide, we grab only the name and the blog URL for demonstration purposes.
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	  private String name;
	  private String blog;

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getBlog() {
	    return blog;
	  }

	  public void setBlog(String blog) {
	    this.blog = blog;
	  }

	  @Override
	  public String toString() {
	    return "User [name=" + name + ", blog=" + blog + "]";
	  }

}
