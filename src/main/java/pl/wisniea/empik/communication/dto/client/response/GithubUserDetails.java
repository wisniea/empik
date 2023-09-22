package pl.wisniea.empik.communication.dto.client.response;

import lombok.Data;

import java.util.Date;

@Data
public class GithubUserDetails {
   private String login;
   private long id;
   private String node_id;
   private String avatar_url;
   private String gravatar_id;
   private String url;
   private String html_url;
   private String followers_url;
   private String following_url;
   private String gists_url;
   private String starred_url;
   private String subscriptions_url;
   private String organizations_url;
   private String repos_url;
   private String events_url;
   private String received_events_url;
   private String type;
   private boolean site_admin;
   private String name;
   private String company;
   private String blog;
   private String location;
   private String email;
   private boolean hireable;
   private String bio;
   private String twitter_username;
   private long public_repos;
   private long public_gists;
   private long followers;
   private long following;
   private Date created_at;
   private Date updated_at;
}
