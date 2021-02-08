package backend.controller;

import backend.domain.Hashtag;
import backend.service.HashtagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/v1/hashtag")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class HashtagController {
    private final HashtagService hashtagService;

    @PostMapping
    @ApiOperation(value="해쉬태그 등록", notes="해쉬태그 등록")
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
