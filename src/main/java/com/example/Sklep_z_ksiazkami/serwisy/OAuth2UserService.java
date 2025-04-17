package com.example.Sklep_z_ksiazkami.serwisy;
/*
@Service
public class  OAuth2UserService extends DefaultOAuth2UserService {

        private final UserRepo userRepo;
        private final ClientRepo clientRepo;
        Logger logger = LoggerFactory.getLogger(OAuth2UserService.class);

    public OAuth2UserService(UserRepo userRepo, ClientRepo clientRepo) {
        this.userRepo = userRepo;
        this.clientRepo = clientRepo;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
        logger.info("Load user {}", oAuth2UserRequest);
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
            Oauth2UserDto userInfoDto = new Oauth2UserDto(
                    oAuth2User.getAttributes().get("sub").toString(),
                    oAuth2User.getAttributes().get("name").toString(),
                    oAuth2User.getAttributes().get("email").toString(),
                    oAuth2User.getAttributes().get("picture").toString());

            logger.info("User info is {}", userInfoDto);
            logger.info("Security Context: {}", SecurityContextHolder.getContext().getAuthentication());
            Optional<User> userOptional = userRepo.findByLogin(userInfoDto.getEmail());
            logger.info("User is {}", userOptional);
            User user;
            if (userOptional.isEmpty()){
                user = registerNewUser(userInfoDto);
            } else {
                user = userOptional.get();
                user.setName(userInfoDto.getName());
                user.setPicture(userInfoDto.getPicture());
                userRepo.save(user);
            }
            return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(Oauth2UserDto userInfoDto) {
            User user = new User();
            Client client = new Client();
            user.setName(userInfoDto.getName());
            user.setLogin(userInfoDto.getEmail());
            clientRepo.save(client);
            return userRepo.save(user);
    }
}
*/