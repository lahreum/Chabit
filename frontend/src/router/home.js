import Home from '../views/home/Home.vue'
import HomeRanking from '../views/home/HomeRanking.vue'
import HotReviewList from '../views/home/HotReviewList.vue'
import UpcomingChallengeList from '../views/home/UpcomingChallengeList.vue'

export default[
    {
      path: '/home',
      name: 'Home',
      component: Home,
      children: [
        {
          path: 'home-ranking',
          name: 'HomeRanking',
          component: HomeRanking
        },
        {
          path: 'hot-review-list',
          name: 'HotReviewList',
          component: HotReviewList
        },
        {
          path: 'upcoming-challenge-list',
          name: 'UpcomingChallengeList',
          component: UpcomingChallengeList
        },
      ]
    }
]