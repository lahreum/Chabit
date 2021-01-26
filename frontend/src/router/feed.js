import Feed from '../views/feed/Feed.vue'
import MyChallenge from '../views/feed/MyChallenge.vue'
import TodayChallengeList from '../views/feed/TodayChallengeList.vue'
import MyReview from '../views/feed/MyReview.vue'
import ReviewList from '../views/feed/ReviewList.vue'
import Reward from '../views/feed/Reward.vue'
import ReviewDetail from '../views/feed/ReviewDetail.vue'
import ReviewComment from '../views/feed/ReviewComment.vue'

export default[
{
    path: '/feed',
    name: 'Feed',
    component: Feed,
    children: [
      {
        path: 'my-challenge',
        name: 'MyChallenge',
        component: MyChallenge,
      },
      {
        path: 'my-challenge/today-challenge-list',
        name: 'TodayChallengeList',
        component: TodayChallengeList
      },
      {
        path: 'my-review',
        name: 'MyReview',
        component: MyReview
      },
      {
        path: 'my-review/review-list',
        name: 'ReviewList',
        component: ReviewList
      },
      {
        path: 'my-review/review-detail',
        name: 'ReviewDetail',
        component: ReviewDetail
      },
      {
        path: 'my-review/review-detail/review-comment',
        name: 'ReviewComment',
        component: ReviewComment
      },
      {
        path: 'reward',
        name: 'Reward',
        component: Reward
      },
    ]
    }
]