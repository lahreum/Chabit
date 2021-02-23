import Follow from '../views/follow/Follow.vue'
import FollowList from '../views/follow/FollowList.vue'
import FollowSearchList from '../views/follow/FollowSearchList.vue'
import FollowingList from '../views/follow/FollowingList.vue'

export default[
  {
    path: '/follow',
    name: 'Follow',
    component: Follow,
    children: [
      {
        path: '',
        name: 'FollowList',
        component: FollowList
      },
      {
        path: 'follow-search-list',
        name: 'FollowSearchList',
        component: FollowSearchList
      },
      {
        path: 'following-list',
        name: 'FollowingList',
        component: FollowingList
      },
    ]
  },
]