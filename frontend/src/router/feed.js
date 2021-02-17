import Feed from '../views/feed/Feed.vue';
import MyChallenge from '../views/feed/MyChallenge.vue';
import TodayChallengeList from '../views/feed/TodayChallengeList.vue';
import MyReview from '../views/feed/MyReview.vue';
import Reward from '../views/feed/Reward.vue';
import ReviewDetail from '../views/feed/ReviewDetail.vue';
import ReviewComment from '../views/feed/ReviewComment.vue';
import YourFeed from '../views/feed/YourFeed.vue';
import YourReview from '../views/feed/YourReview.vue';
import YourReviewDetail from '../views/feed/YourReviewDetail.vue';
import YourReviewComment from '../views/feed/YourReviewComment.vue';
import YourReward from '../views/feed/YourReward.vue';


export default [
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
        component: TodayChallengeList,
      },
      {
        path: 'my-review',
        name: 'MyReview',
        component: MyReview,
      },
      {
        path: 'reward',
        name: 'Reward',
        component: Reward,
      },
    ],
  },
  {
    path: '/review-detail',
    name: 'ReviewDetail',
    component: ReviewDetail,
  },
  {
    path: '/review-comment',
    name: 'ReviewComment',
    component: ReviewComment,
  },
  {
    path: '/your-feed',
    name: 'YourFeed',
    component: YourFeed,
  },
  {
    path: '/your-review',
    name: 'YourReview',
    component: YourReview,
  },
  {
    path: '/your-review-detail',
    name: 'YourReviewDetail',
    component: YourReviewDetail,
  },
  {
    path: '/your-review-comment',
    name: 'YourReviewComment',
    component: YourReviewComment,
  },
  {
    path: '/your-review-reward',
    name: 'YourReward',
    component: YourReward,
  },
];
