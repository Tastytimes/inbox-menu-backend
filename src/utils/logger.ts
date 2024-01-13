import pino from "pino";
import dayjs from "dayjs";
import pretty from "pino-pretty";
const logger = pino(pretty())

export default logger;