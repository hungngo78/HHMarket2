using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Web;
using System.Web.Security;
using FormsAuth;
using FormsAuth.Models;
using HHMarketWebApp.Models;
using HHMarketWebApp.Services;

namespace HHMarketWebApp.Provider
{
    public class MyMembershipProvider: MembershipProvider
    {        
        /// <summary>
        /// Authenticates the user.
        /// </summary>
        ///<param name="username">Username</param>
        ///<param name="password">Password</param>
        /// <returns>bool</returns>
        public override bool ValidateUser(string username, string password)
        {
            // Lookup user in database, web service, etc. We'll just generate a fake user for this demo.
            AccountService service = new AccountService();
            Models.User userEntity = Task.Run(() => service.authenticate(username, password)).Result;
        
            if (userEntity != null)
            {
                // Store the user temporarily in the context for this request.
                FormsAuth.Models.User user = new FormsAuth.Models.User {
                            Id = userEntity.UserId,
                            Username = userEntity.UserName
                };
                HttpContext.Current.Items.Add("User", user);

                return true;
            }
            else
            {
                return false;
            }
        }

        /// <summary>
        /// Retrieves the user details for a particular user.
        /// </summary>
        ///<param name="username">User to retrieve user details for</param>
        ///<param name="userIsOnline"></param>
        /// <returns>MembershipUser</returns>
        public override MembershipUser GetUser(string username, bool userIsOnline)
        {
            if (UserManager.User != null)
            {
                return new MembershipUser("MyMembershipProvider", username, UserManager.User.Id, UserManager.User.Username, null, null, true, false, DateTime.MinValue, DateTime.MinValue, DateTime.MinValue, DateTime.MinValue, DateTime.MinValue);
            }
            else
            {
                return null;
            }
        }

        #region Not Used
        public override string ApplicationName
        {
            get
            {
                throw new NotImplementedException();
            }
            set
            {
                throw new NotImplementedException();
            }
        }

        public override bool ChangePassword(string username, string oldPassword, string newPassword)
        {
            throw new NotImplementedException();
        }

        public override bool ChangePasswordQuestionAndAnswer(string username, string password, string newPasswordQuestion, string newPasswordAnswer)
        {
            throw new NotImplementedException();
        }

        public override MembershipUser CreateUser(string username, string password, string email, string passwordQuestion, string passwordAnswer, bool isApproved, object providerUserKey, out MembershipCreateStatus status)
        {
            throw new NotImplementedException();
        }

        public override bool DeleteUser(string username, bool deleteAllRelatedData)
        {
            throw new NotImplementedException();
        }

        public override bool EnablePasswordReset
        {
            get { throw new NotImplementedException(); }
        }

        public override bool EnablePasswordRetrieval
        {
            get { throw new NotImplementedException(); }
        }

        public override MembershipUserCollection FindUsersByEmail(string emailToMatch, int pageIndex, int pageSize, out int totalRecords)
        {
            throw new NotImplementedException();
        }

        public override MembershipUserCollection FindUsersByName(string usernameToMatch, int pageIndex, int pageSize, out int totalRecords)
        {
            throw new NotImplementedException();
        }

        public override MembershipUserCollection GetAllUsers(int pageIndex, int pageSize, out int totalRecords)
        {
            throw new NotImplementedException();
        }

        public override int GetNumberOfUsersOnline()
        {
            throw new NotImplementedException();
        }

        public override string GetPassword(string username, string answer)
        {
            throw new NotImplementedException();
        }

        public override string GetUserNameByEmail(string email)
        {
            throw new NotImplementedException();
        }

        public override int MaxInvalidPasswordAttempts
        {
            get { throw new NotImplementedException(); }
        }

        public override int MinRequiredNonAlphanumericCharacters
        {
            get { throw new NotImplementedException(); }
        }

        public override int MinRequiredPasswordLength
        {
            get { throw new NotImplementedException(); }
        }

        public override int PasswordAttemptWindow
        {
            get { throw new NotImplementedException(); }
        }

        public override MembershipPasswordFormat PasswordFormat
        {
            get { throw new NotImplementedException(); }
        }

        public override string PasswordStrengthRegularExpression
        {
            get { throw new NotImplementedException(); }
        }

        public override bool RequiresQuestionAndAnswer
        {
            get { throw new NotImplementedException(); }
        }

        public override bool RequiresUniqueEmail
        {
            get { throw new NotImplementedException(); }
        }

        public override string ResetPassword(string username, string answer)
        {
            throw new NotImplementedException();
        }

        public override bool UnlockUser(string userName)
        {
            throw new NotImplementedException();
        }

        public override void UpdateUser(MembershipUser user)
        {
            throw new NotImplementedException();
        }

        public override MembershipUser GetUser(object providerUserKey, bool userIsOnline)
        {
            throw new NotImplementedException();
        }
        #endregion
    }
}