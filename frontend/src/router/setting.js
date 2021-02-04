import Setting from "../views/setting/Setting.vue";
import AlarmSetting from "../views/setting/AlarmSetting.vue";
import UserSetting from "../views/setting/UserSetting.vue";
import ModifyUser from "../views/setting/ModifyUser.vue";
import ManageMember from "../views/setting/ManageMember.vue";
import MemberList from "../views/setting/MemberList.vue";

export default [
  {
    path: "/setting",
    name: "Setting",
    component: Setting,
    children: [
      {
        path: "alarm-setting",
        name: "AlarmSetting",
        component: AlarmSetting,
      },
      {
        path: "user-setting",
        name: "UserSetting",
        component: UserSetting,
      },
    ],
  },
  {
    path: "/modify-user",
    name: "ModifyUser",
    component: ModifyUser,
  },
  {
    path: "/manage-member",
    name: "ManageMember",
    component: ManageMember,
    children: [
      {
        path: "member-list",
        name: "MemberList",
        component: MemberList,
      },
    ],
  },
];
