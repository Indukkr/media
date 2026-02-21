package com.social.media;

import com.social.media.models.Post;
import com.social.media.models.SocialGroup;
import com.social.media.models.SocialProfile;
import com.social.media.models.SocialUser;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.SocialGroupRepository;
import com.social.media.repositories.SocialProfileRepository;
import com.social.media.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    private final SocialUserRepository userRepository;
    private final SocialGroupRepository groupRepository;
    private final SocialProfileRepository profileRepository;
    private final PostRepository postRepository;

    public DataInitializer(PostRepository postRepository, SocialUserRepository socialUserRepository, SocialGroupRepository socialGroupRepository, SocialProfileRepository socialProfileRepository) {
        this.postRepository = postRepository;
        this.userRepository = socialUserRepository;
        this.groupRepository = socialGroupRepository;
        this.profileRepository = socialProfileRepository;
    }

    @Bean
    public CommandLineRunner initializeDat(){
        return args ->{

            // create some users
            SocialUser user1 = new SocialUser();
            SocialUser user2 = new SocialUser();
            SocialUser user3 = new SocialUser();

            //  save users to the database
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            // create some group
            SocialGroup group1 = new SocialGroup();
            SocialGroup group2 = new SocialGroup();

            // add users to the group
            group1.getSocialUsers().add(user1);
            group1.getSocialUsers().add(user2);

            group2.getSocialUsers().add(user2);
            group2.getSocialUsers().add(user3);

            // save group to the database
            groupRepository.save(group1);
            groupRepository.save(group2);


            // Associate users to the groups
            user1.getGroups().add(group1);
            user2.getGroups().add(group1);
            user2.getGroups().add(group2);
            user3.getGroups().add(group2);

            // save users back to database to update the association
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            // create some posts
            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            // Associate posts with users
            post1.setSocialUser(user1);
            post2.setSocialUser(user2);
            post3.setSocialUser(user3);

            // save posts to the database
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            // create some social profile
            SocialProfile profile1 = new SocialProfile();
            SocialProfile profile2 = new SocialProfile();
            SocialProfile profile3 = new SocialProfile();

            // Associate profiles with users
            profile1.setUser(user1);
            profile2.setUser(user2);
            profile3.setUser(user3);

            // save profiles to the database
            profileRepository.save(profile1);
            profileRepository.save(profile2);
            profileRepository.save(profile3);

        };


    }
}
