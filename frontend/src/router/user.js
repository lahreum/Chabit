import Login from '../views/user/Login.vue'
import Join from '../views/user/Join.vue'
import Find from '../views/user/Find.vue'
import FindPw from '../views/user/FindPw.vue'
import FindEmail from '../views/user/FindEmail.vue'
import FindEmailFinish from '../views/user/FindEmailFinish.vue'
import ChangePw from '../views/user/ChangePw.vue'


export default [
    {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/find',
    name: 'Find',
    component: Find,
    children: [
      {
        path: 'find-pw',
        name: 'FindPw',
        component: FindPw
      },
      {
        path: 'find-email',
        name: 'FindEmail',
        component: FindEmail
      },
    ]
    },
    {
      path: '/change-pw',
      name: 'ChangePw',
      component: ChangePw
    },
    {
      path: '/find-email-finish',
      name: 'FindEmailFinish',
      component: FindEmailFinish
    },
]