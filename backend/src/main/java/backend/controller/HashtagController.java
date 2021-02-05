package backend.controller;

import backend.domain.Hashtag;
import backend.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hashtag")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class HashtagController {
    private final HashtagService hashtagService;

    @PostMapping
    public BaseResponse addHashtag(@RequestBody String hashtagName){
        BaseResponse response = null;
        try {
            Hashtag hashtag = hashtagService.makeHashtag(hashtagName);
            response = new BaseResponse("success", hashtag);
        } catch (IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
}
