import Follow from '../views/follow/Follow.vue'
import FollowList from '../views/follow/FollowList.vue'
import FollowSearchList from '../views/follow/FollowSearchList.vue'

export default[
    {
        path: '/follow',
        name: 'Follow',
        component: Follow,
        children: [
          {
            path: 'follow-list',
            name: 'FollowList',
            component: FollowList
          },
          {
            path: 'follow-search-list',
            name: 'FollowSearchList',
            component: FollowSearchList
          },
        ]
      }
]