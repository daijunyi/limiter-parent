local token_key = KEYS[1]
local rate = tonumber(ARGV[1])
local uuid = ARGV[2]
local now = tonumber(ARGV[3])
local rangeTime = tonumber(ARGV[4])

local preOneSecond = now - rangeTime;

local preThirdSecond = now - rangeTime - 3000;

local result = 1

local num = tonumber(redis.call("ZCOUNT", token_key , preOneSecond, now))

if( num >=  rate )
then
   result = 0
else
   result = 1
   redis.call("ZADD", token_key , now, uuid)
end
  redis.call("ZREMRANGEBYSCORE", token_key , 0, preThirdSecond)
 return result

